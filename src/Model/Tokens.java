package Model;

/**
 *
 * @author Chriss Yañez
 */
public class Tokens {

    private final int liena, token;
    private final String lexema;
    private final String sintaxis;

    public Tokens(int liena, int token, String lexema) {
        this.liena = liena;
        this.token = token;
        this.lexema = lexema;
        this.sintaxis = setSintaxis(token);
    }

    private String setSintaxis(int token) {
        switch (token) {
            case -1:
                return "id";
            case -2:
            case -3:
                return "";
            case -4:
                return "if";
            case -5: //then
                return "";
            case -6:
                return "while";
            case -7: //do
                return "";
            case -8:
                return "for";
            case -9: //func
                return "";
            case -10: //proc
                return "";
            case -11:
                return "switch";
            case -12: //int
                return "";
            case -13: //String
                return "";
            case -14: //float
                return "";
            case -15: //char
                return "";
            case -16: //BOOLEAN
                return "";
            case -17:
                return "Cont_true";
            case -18:
                return "Cont_false";
            case -19:
                return "Cont_entero";
            case -20:
                return "Cont_cadena";
            case -21:
                return "Cont_real";
            case -22:
                return "Cont_caracter";
            case -23:
                return "Cont_exponencial";
            case -24:
                return "+";
            case -25:
                return "*";
            case -26:
                return "-";
            case -27:
                return "/";
            case -28:
                return "%";
            case -29: //^
                return "";
            case -30:
                return "++";
            case -31:
                return "--";
            case -32:
                return "!";
            case -33:
                return "&&";
            case -34:
                return "||";
            case -35:
                return "#";
            case -36:
                return "<";
            case -37:
                return "<=";
            case -38:
                return ">";
            case -39:
                return ">=";
            case -40: //==
                return "==";
            case -41:
                return "!=";
            case -42:
                return "<<";
            case -43:
                return ">>";
            case -44:
                return "|";
            case -45:
                return "&";
            case -46:
                return ";";
            case -47:
                return ",";
            case -48:
                return ".";
            case -49:
                return "(";
            case -50:
                return ")";
            case -51:
                return "[";
            case -52:
                return "]";
            case -53:
                return "{";
            case -54:
                return "}";
            case -55:
                return "=";
            case -56:
                return "+=";
            case -57:
                return "-=";
            case -58:
                return "*=";
            case -59:
                return "/=";
            case -60:
                return "reg";
            case -61:
                return "main";
            case -62:
                return "REAL";
            case -63:
                return "BOOL";
            case -64:
                return "EXP";
            case -65:
                return "REG";
            case -66:
                return "VOID";
            case -67:
                return "FILE";
            case -68:
                return "∶";
            case -69:
                return "**";
            case -70:
                return "clean";
            case -71:
                return "sqrt";
            case -72:
                return "sqr";
            case -73:
                return "pow";
            case -74:
                return "sqrtv";
            case -75:
                return "ins";
            case -76:
                return "conv";
            case -77:
                return "up";
            case -78:
                return "low";
            case -79:
                return "len";
            case -80:
                return "asc";
            case -81:
                return "val";
            case -82:
                return "setcolorb";
            case -83:
                return "setcolorf";
            case -84:
                return "getcolorb";
            case -85:
                return "getcolorf";
            case -86:
                return "~";
            case -87:
                return ">+";
            case -88:
                return "<+";
            case -89:
                return "=>";
            case -90:
                return "=<";
            case -91:
                return "repeat";
            case -92:
                return "return";
            case -93:
                return "break";
            case -94:
                return "until";
            case -95:
                return "else";
            case -96:
                return "$";
            case -97:
                return "INT";
            case -98:
                return "CHAR";
            case -99:
                return "default";
            case -100:
                return "case";
            default:
                return "";
        }
    }

    public String getSintaxis() {
        return sintaxis;
    }

    public int getLiena() {
        return liena;
    }

    public int getToken() {
        return token;
    }

    public String getLexema() {
        return lexema;
    }
    
    @Override
    public String toString() {
        return "Lexema: " + getLexema() + " Linea: " + getLiena();
    }

}
