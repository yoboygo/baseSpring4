/**
 * DateCustomEditorRegister.java
 * 
 * Aimy
 * 上午9:11:01
 */
package tk.codecube.test.propertyediter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

/**
 * @author Aimy
 * 2014年10月14日 上午9:11:02
 */
public class DateCustomEditorRegister implements PropertyEditorRegistrar{

	/* (non-Javadoc)
	 * @see org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors(org.springframework.beans.PropertyEditorRegistry)
	 */
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}


}
