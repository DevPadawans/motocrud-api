package com.devpadawans.motocrudapi.commons.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PathUtils {

    public static final String MEMBROS = "Membros";

    public static final String RESOURCE_MEMBROS = "membros";

    public static final String RESOURCE_SEARCH = "search";
    public static final String RESOURCE_FILTER = "filtrar";
    public static final String RESOURCE_ADD = "adicionar";
    public static final String RESOURCE_REMOVE = "remover";
    public static final String RESOURCE_UPDATE = "atualizar";

    public static final String SLASH = "/";

    public static String joinStringURL(String ... sections) {
        return Arrays.stream(sections).map(Object::toString).collect(Collectors.joining(SLASH));
    }


}
