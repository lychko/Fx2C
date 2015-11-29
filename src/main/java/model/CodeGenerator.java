package model;

import utils.OsUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    public List<String> StaticImports = new ArrayList();
    public List<String> StarImports = new ArrayList();
    public String packageName= "";
    public String className = "";
    public String  ControllerType= "";

    public String ViewType= "";

    public List<String>  BuildControlsLines=new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    void appendln(String text){
        stringBuilder.append(text);
        stringBuilder.append('\n');
        stringBuilder.append('\r');
    }

    void append(String text){
        stringBuilder.append(text);
    }

    public String  generateCode(){

        if (!OsUtils.isNullOrEmpty(packageName)) {
            append("package ");
            append(packageName);
            appendln(";");
            appendln();
        }
        for (String imprt : StaticImports) {
            append("import ");
            append(imprt);
            appendln(";");
        }
        appendln();
        for (String imprt : StarImports) {
            append("import ");
            append(imprt);
            appendln(".*;");
        }

        appendln();

        append("public final class ");
        append(className);
        appendln(" {");

        if (!OsUtils.isNullOrEmpty(ControllerType)) {
            append("        public ");
            append(ControllerType);
            appendln(" _controller;");
        }

        append("        public ");
        append(ViewType);
        appendln(" _view;");


        append("public ");
        append(className);
        appendln("() {");


        if (!OsUtils.isNullOrEmpty(ControllerType)) {

            append("        _controller = new ");
            append(ControllerType);
            appendln("();");
        }

        for (String line : this.BuildControlsLines) {
            append("        ");
            append(line);
            appendln(";");
        }
        appendln("}");


        appendln("}");


        return stringBuilder.toString();
    }

    private void appendln() {
        appendln("");
    }

}