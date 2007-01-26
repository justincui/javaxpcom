package browser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.MozillaBrowser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mozilla.xpcom.Mozilla;
import org.mozilla.xpcom.nsIComponentManager;
import org.mozilla.xpcom.nsIDOMElement;
import org.mozilla.xpcom.nsIDOMDocument;
import org.mozilla.xpcom.nsIDOMNode;
import org.mozilla.xpcom.nsIDOMNodeList;
import org.mozilla.xpcom.nsIDOMSerializer;
import org.mozilla.xpcom.nsIDOMXPathEvaluator;
import org.mozilla.xpcom.nsIDOMXPathNSResolver;
import org.mozilla.xpcom.nsIDOMXPathResult;
import org.mozilla.xpcom.nsISupports;


public class XPathJVMCrash {

        protected Display display;

        //MozillaBrowser as found in
        protected MozillaBrowser browser;

        //XPCOM XPath evaluator
        protected nsIDOMXPathEvaluator xpathEval;

        // 
        protected nsIDOMSerializer serializador;
        
        public static void main(String[] args) {
                new XPathJVMCrash();
        }

        public XPathJVMCrash() {

                initBrowser();

        }

        private void initBrowser() {

                display = new Display();
                final Shell shell = new Shell(display);
                shell.setLayout(new FillLayout());
                browser = new MozillaBrowser(shell,SWT.NONE);
                
//                String url = "http://www.google.com";
                 String url = "http://phpleague.univert.org/telecharger.php";
                browser.setUrl(url);

                shell.open();
                
                Mozilla moz = Mozilla.getInstance();
                nsIComponentManager componentManager = moz.getComponentManager();
                String NS_IDOMXPATHEVALUATOR_CONTACTID =
"@mozilla.org/dom/xpath-evaluator;1"; //$NON-NLS-1$
                xpathEval = (nsIDOMXPathEvaluator) componentManager
                                .createInstanceByContractID(NS_IDOMXPATHEVALUATOR_CONTACTID,
                                                null, nsIDOMXPathEvaluator.NS_IDOMXPATHEVALUATOR_IID);

                String NS_IDOMXMLSERIALIZER_ID = "@mozilla.org/xmlextras/xmlserializer;1";
                	
                serializador = (nsIDOMSerializer) componentManager.createInstanceByContractID( NS_IDOMXMLSERIALIZER_ID, null, nsIDOMSerializer.NS_IDOMSERIALIZER_IID);
                
                
                browser.addProgressListener(new ProgressListener() {
                    public void changed(ProgressEvent event) {
                    }
                    
                    public void completed(ProgressEvent event) {
                    	
                        nsIDOMDocument doc = browser.getDocument();
                        nsIDOMNodeList aNL = doc.getElementsByTagName("a");
                        long aNumber = aNL.getLength();
                        System.out.println("Number of \"a\" elements: " + aNumber + "");
                        test();
                    }
            });
                
                
                while (!shell.isDisposed()) {
                	if (!display.readAndDispatch())
                		display.sleep();
                 }

                     display.dispose();
        }

        private void test() {

            System.out.println("Loading...");
            String query = "/html/body/div/center/table/tbody/tr[4]/td[1]/div/center/table[@id='AutoNumber2']/tbody/tr[3]/td/div//*";
            // String query = "//*[@id='AutoNumber2']//*";
            // String query = "//*[@class='q']";
                for(nsIDOMNode node: xpathNodes(query, browser.getDocument())){
                            nsIDOMElement element = (nsIDOMElement) node.queryInterface(nsIDOMElement.NS_IDOMELEMENT_IID);
//                             nsIDOMNodeList listado = element.getChildNodes(); 
//                             for (int i = 0; i< listado.getLength(); i++ )
//                            	 System.out.println(  listado.item(i).getFirstChild().getNodeValue() );
                             // String text1 = element.getFirstChild().getNodeValue();
                             // System.out.println(text1 + "#");
                             System.out.println(serializador.serializeToString(element.getFirstChild()));
                             
                }
            
            System.out.println();
    }

        public List<nsIDOMNode> xpathNodes(String xpath, nsIDOMNode context) {
        		
                nsIDOMDocument document = browser.getDocument();

                nsIDOMXPathNSResolver res = xpathEval.createNSResolver(document);
                nsISupports obj = xpathEval.evaluate(xpath, context, res,
                                nsIDOMXPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);

                nsIDOMXPathResult result = (nsIDOMXPathResult) obj
                                .queryInterface(nsIDOMXPathResult.NS_IDOMXPATHRESULT_IID);

                List<nsIDOMNode> rNodes = new ArrayList<nsIDOMNode>();

                
                for(int i=0; i<result.getSnapshotLength(); i++){
                        rNodes.add(result.snapshotItem(i));
                }

                return rNodes;
        }

}
