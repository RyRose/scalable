package parse;

public class Grammar extends edu.hendrix.grambler.Grammar {
    public Grammar() {
        super();
        addProduction("lines", new String[]{"lines", "line"}, new String[]{"line"});
        addProduction("line", new String[]{"sp", "first_cmd", "newline"}, new String[]{"sp", "first_cmd"});
        addProduction("first_cmd", new String[]{"first_cmd", "sp", "cmd"}, new String[]{"cmd"});
        addProduction("cmd", new String[]{"repeat_cmd"}, new String[]{"ifelse_cmd"}, new String[]{"if_cmd"}, new String[]{"procedure_cmd"}, new String[]{"call_cmd"});
        addProduction("no_arg_cmd", new String[]{"no_arg_cmds"});
        addProduction("arg_cmd", new String[]{"arg_cmds", "sp", "num"});
        addProduction("repeat_cmd", new String[]{"'repeat'", "sp", "num", "sp", "bracket"});
        addProduction("if_cmd", new String[]{"\"if[^else]\"", "sp", "cond_statement", "sp", "bracket"});
        addProduction("ifelse_cmd", new String[]{"'ifelse'", "sp", "cond_statement", "sp", "bracket", "sp", "bracket"});
        addProduction("procedure_cmd", new String[]{"'to'", "sp", "name", "sp", "param", "sp", "bracket"});
        addProduction("call_cmd", new String[]{"no_arg_cmd"}, new String[]{"arg_cmd"}, new String[]{"custom_procedure_cmd"});
        addProduction("custom_procedure_cmd", new String[]{"name", "sp", "sp", "num"});
        addProduction("cond_statement", new String[]{"cond_statement", "sp", "linker", "sp", "cond_paren"}, new String[]{"cond_paren"});
        addProduction("cond_paren", new String[]{"'not'", "sp", "cond_paren"}, new String[]{"'('", "sp", "num", "sp", "cond", "sp", "num", "sp", "')'"}, new String[]{"num", "sp", "cond", "sp", "num"});
        addProduction("bracket", new String[]{"'['", "sp", "first_cmd", "sp", "']'"});
        addProduction("arg_cmds", new String[]{"fd"}, new String[]{"bk"}, new String[]{"lt"}, new String[]{"rt"});
        addProduction("no_arg_cmds", new String[]{"pd"}, new String[]{"pu"}, new String[]{"home"}, new String[]{"cs"}, new String[]{"st"}, new String[]{"ht"});
        addProduction("pd", new String[]{"'pendown'"}, new String[]{"'pd'"});
        addProduction("pu", new String[]{"'penup'"}, new String[]{"'pu'"});
        addProduction("home", new String[]{"'home'"});
        addProduction("cs", new String[]{"'clearscreen'"}, new String[]{"'cs'"});
        addProduction("st", new String[]{"'showturtle'"}, new String[]{"'st'"});
        addProduction("ht", new String[]{"'hideturtle'"}, new String[]{"'ht'"});
        addProduction("fd", new String[]{"'forward'"}, new String[]{"'fd'"});
        addProduction("bk", new String[]{"'back'"}, new String[]{"'bk'"});
        addProduction("lt", new String[]{"'left'"}, new String[]{"'lt'"});
        addProduction("rt", new String[]{"'right'"}, new String[]{"'rt'"});
        addProduction("cond", new String[]{"'<='"}, new String[]{"\"<[^=]\""}, new String[]{"'='"}, new String[]{"\">[^=]\""}, new String[]{"'>='"});
        addProduction("linker", new String[]{"and"}, new String[]{"or"});
        addProduction("sp", new String[]{"\" *\""});
        addProduction("newline", new String[]{"\" *\\n\""}, new String[]{"\" *\\r\\n\""});
        addProduction("and", new String[]{"'and'"});
        addProduction("or", new String[]{"'or'"});
        addProduction("name", new String[]{"\"[a-z]+\""});
        addProduction("param", new String[]{"\":[a-z]+\""});
        addProduction("number", new String[]{"\"\\d+\""});
        addProduction("num", new String[]{"number"}, new String[]{"param"}, new String[]{"expr"});
        addProduction("expr", new String[]{"expr", "sp", "as", "sp", "expr2"}, new String[]{"expr2"});
        addProduction("expr2", new String[]{"expr2", "sp", "md", "sp", "paren"}, new String[]{"paren"});
        addProduction("paren", new String[]{"'('", "sp", "expr", "sp", "')'"}, new String[]{"num"});
        addProduction("md", new String[]{"'/'"}, new String[]{"'*'"});
        addProduction("as", new String[]{"'+'"}, new String[]{"'-'"});
    }
}

