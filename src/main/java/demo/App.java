/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo;
import java.net.MalformedURLException;


public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException {
        TestCases tests = new TestCases(); // Initialize your test class
        //TODO: call your test case functions one after other here
        tests.testCase01();   
        tests.TestCase02();   
        tests.TestCase03();  
        tests.TestCase04();
        tests.TestCase05();
        tests.TestCase06();
        //END Tests
        tests.endTest(); // End your test by clearning connections and closing browser
    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}
