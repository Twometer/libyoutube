package de.twometer.libyoutube.decoder;

import de.twometer.libyoutube.util.Regex;
import de.twometer.libyoutube.util.StringUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignatureDecoder {

    private static final Pattern functionRegex = Pattern.compile("\\w+\\.(\\w+)\\(");

    private String jsPlayer;
    private String signature;

    public SignatureDecoder(String jsPlayer, String signature) {
        this.jsPlayer = jsPlayer;
        this.signature = signature;
    }

    public String decode() {
        String[] functionLines = getDecryptionFunctionLines(jsPlayer);
        JsDecrypter decryptor = new JsDecrypter();

        String deciphererDefinitionName = Regex.match(String.join(";", functionLines), "(\\w+).\\w+\\(\\w+,\\d+\\);").group(1);
        if (StringUtility.isEmpty(deciphererDefinitionName))
            throw new RuntimeException("Cannot find decoder def name");

        String deciphererDefinitionBody = Regex.match(jsPlayer, "var\\s+" + Pattern.quote(deciphererDefinitionName) + "=\\{(\\w+:function\\(\\w+(,\\w+)?\\)\\{(.*?)\\}),?\\};", Pattern.DOTALL).group(0);
        if (StringUtility.isEmpty(deciphererDefinitionName))
            throw new RuntimeException("Cannot find decoder def body");

        for (String functionLine : functionLines) {
            if (decryptor.isComplete())
                break;
            Matcher matcher = functionRegex.matcher(functionLine);
            if (matcher.find()) {
                decryptor.addFunction(deciphererDefinitionBody, matcher.group(1));
            }
        }

        for (String functionLine : functionLines) {
            Matcher matcher = functionRegex.matcher(functionLine);
            if (matcher.find())
                signature = decryptor.executeFunction(signature, functionLine, matcher.group(1));
        }

        return signature;
    }

    private String[] getDecryptionFunctionLines(String js) {
        Matcher deciphererFuncName = Pattern.compile("(\\w+)=function\\(\\w+\\)\\{(\\w+)=\\2\\.split\\(\\x22{2}\\);.*?return\\s+\\2\\.join\\(\\x22{2}\\)}").matcher(js);
        if (deciphererFuncName.find()) {
            Matcher deciphererFuncBody = Pattern.compile("(?!h\\.)" + Pattern.quote(deciphererFuncName.group(1)) + "=function\\(\\w+\\)\\{(.*?)\\}", Pattern.DOTALL).matcher(js);
            if (deciphererFuncBody.find()) {
                return deciphererFuncBody.group(1).split(";");
            }
        }
        throw new RuntimeException("Cannot find signature decryptor lines");
    }

}
