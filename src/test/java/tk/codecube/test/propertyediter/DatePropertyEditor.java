/**
 * DatePropertyEditor.java
 * 
 * Aimy
 * 下午10:46:50
 */
package tk.codecube.test.propertyediter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Aimy
 * 2014年10月13日 下午10:46:50
 */
public class DatePropertyEditor extends PropertyEditorSupport{
	
	private String format = "yyyy-MM-dd";

	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public void setAsText(String text) throws java.lang.IllegalArgumentException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (text instanceof String) {
            try {
				setValue(sdf.parse(text));
			} catch (ParseException e) {
				e.printStackTrace();
			}
            return;
        }
        throw new java.lang.IllegalArgumentException(text);
    }
	

    /**
     * Determines whether the propertyEditor can provide a custom editor.
     *
     * @return  True if the propertyEditor can provide a custom editor.
     */
    public boolean supportsCustomEditor() {
        return true;
    }
}
