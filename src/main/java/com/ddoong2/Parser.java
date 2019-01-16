package com.ddoong2;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final int CONSONANT_START_CODE = 0x1100;
    private static final int SYLLABLE_START_CODE = 0xAC00;
    private static final int SYLLABLE_END_CODE = 0xD7A3;
    private static final int CYCLE = 588;

    private final Map<Integer, Integer> compatibleInitial = new HashMap<>();

    public Parser() {
        int code = 0x1100;
        compatibleInitial.put("ㄱ".codePointAt(0), code++);
        compatibleInitial.put("ㄲ".codePointAt(0), code++);
        compatibleInitial.put("ㄴ".codePointAt(0), code++);
        compatibleInitial.put("ㄷ".codePointAt(0), code++);
        compatibleInitial.put("ㄸ".codePointAt(0), code++);
        compatibleInitial.put("ㄹ".codePointAt(0), code++);
        compatibleInitial.put("ㅁ".codePointAt(0), code++);
        compatibleInitial.put("ㅂ".codePointAt(0), code++);
        compatibleInitial.put("ㅃ".codePointAt(0), code++);
        compatibleInitial.put("ㅅ".codePointAt(0), code++);
        compatibleInitial.put("ㅆ".codePointAt(0), code++);
        compatibleInitial.put("ㅇ".codePointAt(0), code++);
        compatibleInitial.put("ㅈ".codePointAt(0), code++);
        compatibleInitial.put("ㅉ".codePointAt(0), code++);
        compatibleInitial.put("ㅊ".codePointAt(0), code++);
        compatibleInitial.put("ㅋ".codePointAt(0), code++);
        compatibleInitial.put("ㅌ".codePointAt(0), code++);
        compatibleInitial.put("ㅍ".codePointAt(0), code++);
        compatibleInitial.put("ㅎ".codePointAt(0), code++);
    }

    public boolean find(String search, String source) {
        checkValidation(search, source);

        if (isEmptyString(search)) {
            return true;
        }

        if (isLessThenSearchLength(search, source)) {
            return false;
        }

        int startIndex = getStartIndex(source, search.charAt(0));
        if ( startIndex == -1) {
            return false;
        }

        for (int index = startIndex, searchIndex=0; index < startIndex + search.length() ; index++, searchIndex++) {
            if (getCharToInitialSound(search.charAt(searchIndex)) != getCharToInitialSound(source.charAt(index))) {
                return false;
            }
        }

        return true;
    }

    private boolean isLessThenSearchLength(String search, String source) {
        return search.length() > source.length();
    }

    private void checkValidation(String search, String source) {
        if (search == null || source == null) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isEmptyString(String search) {
        return search.equalsIgnoreCase("");
    }

    private int getStartIndex(String source, char findSyllable) {

        for (int index = 0; index < source.length(); index++) {
            if ( getCharToInitialSound(findSyllable) == getCharToInitialSound(source.charAt(index))) {
                return index;
            }
        }
        return -1;
    }

    private char getCharToInitialSound(char syllable) {
        if (isHangul(syllable)) {
            return ((char) (CONSONANT_START_CODE + ((syllable - SYLLABLE_START_CODE) / CYCLE)));
        } else if ( isInputCompatibleInitialSound(syllable) ) {
            return ((char) compatibleInitial.get(Integer.valueOf(syllable)).intValue());
        }

        return syllable;
    }

    private boolean isInputCompatibleInitialSound(char syllable) {
        return compatibleInitial.containsKey(Integer.valueOf(syllable));
    }

    private boolean isHangul(char syllable) {
        return  SYLLABLE_START_CODE <= syllable && syllable <= SYLLABLE_END_CODE;
    }
}
