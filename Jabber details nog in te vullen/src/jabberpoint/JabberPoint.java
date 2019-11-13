package jabberpoint;

import java.io.IOException;

import javax.swing.*;

import jabberpoint.model.Accessor;
import jabberpoint.model.XMLAccessor;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.old.PresentationOld;
import jabberpoint.model.old.StyleOld;
import jabberpoint.model.old.SlideViewerFrameOld;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

/** JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2017/11/12 Randy Pottgens, Ivo Willemsen
 */

public class JabberPoint {
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

	private static final String TITLE = "Jabberpoint Slideshow";

	public static void main(String argv[]) {

		DrawingDriver drawingDriver = DrawingDriverFactory.getInstance();
		drawingDriver.initialize(TITLE);

	}

}
