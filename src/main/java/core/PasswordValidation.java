package core;

import java.io.IOException;
/**
 * Elements Validation
 *
 */
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.*;
import java.io.*;
import java.util.*;
import org.passay.*;
import org.passay.dictionary.*;
import org.passay.dictionary.sort.ArraysSort;


public class PasswordValidation {
	static String in_browser = "Firefox"; // "HtmlUnit" "Firefox" "Chrome"  "Safari"  "IE"  "Edge"
    static WebDriver driver;
    static final String url = "http://alex.academy/exercises/login/";

       public static void main(String[] args) throws InterruptedException, IOException {
/*    	   Browsers.setWebDriver(in_browser);
    	   driver = Browsers.driver;
             
              driver.get(url);
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
              
              String password = "DmitryNah_249";
              Properties p = new Properties();
              p.load(new FileInputStream("./src/resources/test_data/csv/messages.properties"));
              MessageResolver msg = new PropertiesMessageResolver(p);

  ArrayWordList awl = WordLists.createFromReader(
new FileReader[] {
new FileReader("./src/resources/test_data/csv/dictionary.txt")},false,
new ArraysSort());
  
  WordListDictionary dict = new WordListDictionary(awl);
  DictionaryRule dictRule = new DictionaryRule(dict);
  //DictionarySubstringRule dicSubRule = new DictionarySubstringRule(dict);
  //dicSubRule.setMatchBackwards(false);

              CharacterCharacteristicsRule rule = new CharacterCharacteristicsRule();
              rule.setNumberOfCharacteristics(3);

              rule.getRules().add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
              rule.getRules().add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
              rule.getRules().add(new CharacterRule(EnglishCharacterData.Alphabetical, 1));
              rule.getRules().add(new CharacterRule(EnglishCharacterData.Digit, 1));
              rule.getRules().add(new CharacterRule(EnglishCharacterData.Special, 1));

              PasswordValidator validator = new PasswordValidator(msg, Arrays.asList(
                           // Length between 8 and 16 characters
                           new LengthRule(8, 16),
                           // Define elements of N (Alphabetical, Digit, Upper, Lower, Symbol)
                           rule,
                           // No whitespace
                           new WhitespaceRule(),
                           // Dictionary Rule
                           /*dicSubRule*/dictRule));
              RuleResult result = validator.validate(new PasswordData(password));
              if (result.isValid()) {
                     System.out.println("Valid password");
              }
              for (String m : validator.getMessages(result)) {
                     System.err.println(m);
              }

              
              
              
//driver.quit();

}}

