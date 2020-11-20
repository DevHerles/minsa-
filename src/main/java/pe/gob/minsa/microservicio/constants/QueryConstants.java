package pe.gob.minsa.microservicio.constants;

import java.util.regex.Pattern;

public interface QueryConstants {
    Pattern SEARCH_QUERY_PATTERN = Pattern.compile("(OR-)?(N-|S-|D-|B-)(\\w+?)(=|==|<|>|<=|>=|#|!#|!=|!==|%=|=%)(\"([^\"]+)\")");
    String EMPTY = "";
    String DOUBLE_QUOTES = "\"";
    String COMMA = ",";
    String COLON = ":";
    String MATCH = "%%%s%%%";
    String MATCH_END = "s%%%";
    String MATCH_START = "%%%s";
    String EQUALS = "==";
    String NOT_EQUALS = "!==";
    String LIKE = "=";
    String NOT_LIKE = "!=";
    String L_T = "<";
    String G_T = ">";
    String L_T_EQUALS = "<=";
    String G_T_EQUALS = ">=";
    String IN = "#";
    String NOT_IN = "!#";
    String DEFAULT_SORT = "id";
    int DEFAULT_PAGE_SIZE = 30;
    int DEFAULT_PAGE_NUMBER = 0;
}