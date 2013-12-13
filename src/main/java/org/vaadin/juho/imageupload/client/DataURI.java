package org.vaadin.juho.imageupload.client;

import com.google.gwt.http.client.URL;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * A class representing a data URI, e.g.
 * "data:text/plain,this%20is%20a%20data%20URI"
 * 
 * @author Juho Nurminen
 */
public class DataURI {

    private static final RegExp DATA_URL_PATTERN = RegExp.compile("^data:"
            + "([^,;]+)?" // mime type
            + "(;charset=([^,;]+))?" // charset
            + "(;base64)?" // base64
            + ",(.*)$", "i"); // data
    private static final int MIME_TYPE_GROUP_INDEX = 1;
    private static final int CHARSET_GROUP_INDEX = 3;
    private static final int BASE64_GROUP_INDEX = 4;
    private static final int DATA_GROUP_INDEX = 5;

    private static final String DEFAULT_TYPE = "text/plain";
    private static final String DEFAULT_CHARSET = "US-ASCII";

    private final String type;
    private final String charset;
    private final boolean base64;
    private final String data;

    /** Constructs a new data URI from a string. */
    public DataURI(String uri) {
        MatchResult match = DATA_URL_PATTERN.exec(uri);

        String typeGroup = match.getGroup(MIME_TYPE_GROUP_INDEX);
        type = isEmpty(typeGroup) ? DEFAULT_TYPE : typeGroup;

        String charsetGroup = match.getGroup(CHARSET_GROUP_INDEX);
        charset = isEmpty(charsetGroup) ? DEFAULT_CHARSET : charsetGroup;

        String base64Group = match.getGroup(BASE64_GROUP_INDEX);
        base64 = !isEmpty(base64Group);

        data = URL.decode(match.getGroup(DATA_GROUP_INDEX));
    }

    /** @return The MIME type of the data */
    public String getType() {
        return type;
    }

    /** @return The charset of the data */
    public String getCharset() {
        return charset;
    }

    /** @return true if the data is base64 encoded, otherwise false */
    public boolean isBase64() {
        return base64;
    }

    /** @return The data part of the URI, possibly in a base64 encoded form */
    public String getData() {
        return data;
    }

    /** @return The raw data with no base64 encoding */
    public String getBinaryData() {
        return base64 ? base64decode(data) : data;
    }

    @Override
    public String toString() {
        return "data:" + type + ";charset=" + charset
                + (base64 ? ";base64" : "") + URL.encode(data);
    }

    private boolean isEmpty(String matchGroup) {
        return matchGroup == null || matchGroup.equals("");
    }

    private static native String base64encode(String s)
    /*-{
        return $wnd.btoa(s);
    }-*/;

    private static native String base64decode(String s)
    /*-{
        return $wnd.atob(s);
    }-*/;

}
