package de.twometer.libyoutube.decoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignatureDecoder {

    private static final Pattern decryptionFunctionRegex = Pattern.compile("\\bc\\s*&&\\s*d\\.set\\([^,]+\\s*,[^(]*\\(([a-zA-Z0-9$]+)\\(");
    private static final Pattern functionRegex = Pattern.compile("\\w+(?:.|\\[)(\\\"?\\w+(?:\\\")?)\\]?\\(");

    private String jsPlayer;
    private String signature;

    public SignatureDecoder(String jsPlayer, String signature) {
        this.jsPlayer = jsPlayer;
        this.signature = signature;
    }

    public String decode() {
        String[] functionLines = getDecryptionFunctionLines(jsPlayer);
        JsDecrypter decryptor = new JsDecrypter();
        for (String functionLine : functionLines) {
            if (decryptor.isComplete())
                break;
            Matcher matcher = functionRegex.matcher(functionLine);
            if (matcher.find()) {
                decryptor.addFunction(jsPlayer, matcher.group(1));
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
        String decryptionFunction = getDecryptionFunction(js);
        Matcher matcher = Pattern.compile("(?!h\\.)" + Pattern.quote(decryptionFunction) + "=function\\(\\w+\\)\\{(.*?)\\}", Pattern.DOTALL).matcher(js);
        if (!matcher.find())
            throw new RuntimeException("Unable to find decryption function lines");
        return matcher.group(1).split(";");
    }

    private String getDecryptionFunction(String js) {
        Matcher matcher = decryptionFunctionRegex.matcher(js);
        if (!matcher.find())
            throw new RuntimeException("Unable to find decryption function");
        return matcher.group(1);
    }


}
