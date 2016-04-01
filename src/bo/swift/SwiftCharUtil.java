package bo.swift;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class SwiftCharUtil {
private static ConcurrentHashMap _charMap = new ConcurrentHashMap();

static {
    _charMap.put(new Character('<'), new Character(' '));
    _charMap.put(new Character('>'), new Character(' '));
    _charMap.put(new Character(';'), new Character('.'));
    _charMap.put(new Character('`'), new Character(' '));
    _charMap.put(new Character('\"'), new Character(' '));
    _charMap.put(new Character(']'), new Character('}'));
    _charMap.put(new Character('['), new Character('{'));
    _charMap.put(new Character('='), new Character(' '));
    _charMap.put(new Character('_'), new Character(' '));
    _charMap.put(new Character('*'), new Character(' '));
    _charMap.put(new Character('&'), new Character(' '));
    _charMap.put(new Character('^'), new Character(' '));
    _charMap.put(new Character('%'), new Character(' '));
    _charMap.put(new Character('#'), new Character(' '));
    _charMap.put(new Character('!'), new Character('.'));
    _charMap.put(new Character('~'), new Character(' '));

    // \u00c0 \u00c1 \u00c2 \u00c3 \u00c4 \u00c5 \u00c6 => A
    _charMap.put(new Character('\u00c0'), new Character('A'));
    _charMap.put(new Character('\u00c1'), new Character('A'));
    _charMap.put(new Character('\u00c2'), new Character('A'));
    _charMap.put(new Character('\u00c3'), new Character('A'));
    _charMap.put(new Character('\u00c4'), new Character('A'));
    _charMap.put(new Character('\u00c5'), new Character('A'));
    _charMap.put(new Character('\u00c6'), new Character('A'));
    // \uc00c7           => C
    _charMap.put(new Character('\u00c7'), new Character('C'));
    // \u00c8 \u00c9 \u00ca \u00cb     => E
    _charMap.put(new Character('\u00c8'), new Character('E'));
    _charMap.put(new Character('\u00c9'), new Character('E'));
    _charMap.put(new Character('\u00ca'), new Character('E'));
    _charMap.put(new Character('\u00cb'), new Character('E'));
    // \u00cc \u00cd \u00ce \u00cf     => I
    _charMap.put(new Character('\u00cc'), new Character('I'));
    _charMap.put(new Character('\u00cd'), new Character('I'));
    _charMap.put(new Character('\u00ce'), new Character('I'));
    _charMap.put(new Character('\u00cf'), new Character('I'));
    // \u00d1           => N
    _charMap.put(new Character('\u00d1'), new Character('N'));
    // \u00d2 \u00d3 \u00d4 \u00d5 \u00d6 \u00d8     => O
    _charMap.put(new Character('\u00d2'), new Character('O'));
    _charMap.put(new Character('\u00d3'), new Character('O'));
    _charMap.put(new Character('\u00d4'), new Character('O'));
    _charMap.put(new Character('\u00d5'), new Character('O'));
    _charMap.put(new Character('\u00d6'), new Character('O'));
    _charMap.put(new Character('\u00d8'), new Character('O'));
    // \u00d9 \u00da \u00db \u00dc     => U
    _charMap.put(new Character('\u00d9'), new Character('U'));
    _charMap.put(new Character('\u00da'), new Character('U'));
    _charMap.put(new Character('\u00db'), new Character('U'));
    _charMap.put(new Character('\u00dc'), new Character('U'));
    // \u00dd    => Y
    _charMap.put(new Character('\u00dd'), new Character('Y'));
}

public static String convert(String s) {
	String converted = s.toUpperCase();
    Iterator iter = _charMap.keySet().iterator();
    while (iter.hasNext()) {
        Character key = (Character)iter.next();
        Character mapping = (Character)_charMap.get(key);
        converted = converted.replace(key.charValue(), mapping.charValue());
    }
    
    // We now must convert '@' to (at) and
    // '\u20ac' to EUR and we handle this here specifically
    // since it is not a one-to-one character mapping
    int index = converted.indexOf("@");
    while (index > 0) {
        converted = converted.substring(0, index) + "(AT)" + converted.substring(index+1);
        index = converted.indexOf("@");
    }
    index = converted.indexOf('\u20AC');
    while (index > 0) {
        converted = converted.substring(0, index) + "EUR" + converted.substring(index+1);
        index = converted.indexOf('\u20AC');
    }
    //  You may be a victim of Window's handling of the Euro symbol. The correct
    //  Unicode code point is 0x20ac, but in some locales, Windows maps it to
    //  0x80. 
    index = converted.indexOf('\u0080');
    while (index > 0) {
        converted = converted.substring(0, index) + "EUR" + converted.substring(index+1);
        index = converted.indexOf('\u0080');
    }
    
    return converted;
}
}
