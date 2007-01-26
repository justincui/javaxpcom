package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.MozillaBrowser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mozilla.xpcom.nsIDOMDocument;
import org.mozilla.xpcom.nsIDOMNodeList;

/* 
import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
 * 
 */

public class Test {
	
/*
    public static void writeDOM(Node n)
        throws IOException
    {
        try {
            StreamResult sr = new StreamResult(System.out);
            //create transformer
            TransformerFactory trf = TransformerFactory.newInstance();
            Transformer tr = trf.newTransformer();
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.INDENT, "yes");

            //serialize the DOM tree
            tr.transform(new DOMSource(n), sr);
        }
        catch (TransformerException e) {
            //wrap to IOException
            IOException ioe = new IOException();
            ioe.initCause(e);
            throw ioe;
        }
     }
     */
         public static void main(String args[]) {
                 Display display = new Display();
                 Shell shell = new Shell(display);
                 
                 shell.setSize(400, 400);
                 shell.open();
                 


                 final MozillaBrowser browser = new MozillaBrowser(shell,SWT.BORDER);
                 
                 browser.setBounds (shell.getClientArea ());

                 
                 browser.setUrl("http://www.google.com");
                 browser.addProgressListener(new ProgressListener() {
                         public void changed(ProgressEvent event) {
                         }
                         
                         public void completed(ProgressEvent event) {
                             nsIDOMDocument doc = browser.getDocument();
                            /*  Document mozDoc = (Document)
                                    org.mozilla.dom.NodeFactory.getNodeInstance(doc);

                             // and from now on, you can use all the existing java/dom libraries :)
                             // such as XPath2 engine, saxon, xalan, etc working on mozilla documents.

                             // e.g. dumping DOM of the html document to stdout:

                             try  {
                               writeDOM(mozDoc);
                             }catch (IOException exc){
                            	exc.printStackTrace();
                             } */
                                 // System.out.println(doc.getDocumentElement());
                             nsIDOMNodeList aNL = doc.getElementsByTagName("a");
                             long aNumber = aNL.getLength();
                             System.out.println("Number of \"a\" elements: " + aNumber + "");
                         }
                 });

                 while (!shell.isDisposed()) {
                         if (!display.readAndDispatch()) {
                                 display.sleep();
                         }
                 }
         }
}