//
//package edu.brown.cs.student.main.selenium;
//
//import edu.brown.cs.student.main.leaderBoard.StoredDatabase;
//import edu.brown.cs.student.main.Main;
//import edu.brown.cs.student.main.leaderBoard.DatabaseLoader;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;
//import spark.Spark;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
///**
// * Tests that dynamic changes work as expected on the frontend.
// * Make sure you do the following before running these tests (using mvn package):
// * - cd backend
// * - cd frontend
// * - cd table
// * - cd react-table
// * - npm start
// */
//
//public class ReactTableTest {
//  WebDriver driver;
//  private final String leaderboards = "data/leadboards.sqlite3";
//
//
///**
//   * Setup chrome driver.
//   */
//
//  @Before
//  public void setupClass() throws InterruptedException {
//    WebDriverManager.chromedriver().setup();
//    driver = new ChromeDriver();
//    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//    // loading the database from the backend
//    DatabaseLoader databaseLoader = new DatabaseLoader();
//    databaseLoader.setStoredDatabase(new StoredDatabase());
//    List<String> args = new ArrayList<>();
//    args.add("load_database");
//    args.add(leaderboards);
//    databaseLoader.execute(args);
//    Main.setDatabaseLoader(databaseLoader);
//    Main.runSparkServer(4567);
//    Thread.sleep(2000);
//  }
//
//  @Test
//  public void tablesLoaded() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.id("loadButton"));
//    loader.click();
//    WebElement dropdown = driver.findElement(By.id("dropdown"));
//    List<WebElement> options = dropdown.findElements(By.tagName("option"));
//    assertEquals(options.size(),5);
//    assertEquals("sqlite_sequence", options.get(1).getAttribute("value"));
//    assertEquals("horoscopes", options.get(2).getAttribute("value"));
//    assertEquals("tas", options.get(3).getAttribute("value"));
//    assertEquals("ta_horoscope", options.get(4).getAttribute("value"));
//    System.out.println("tables loaded test passed");
//  }
//
//  @Test
//  public void newContentAppearsNewSelection() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.id("loadButton"));
//
//    WebElement table0 = driver.findElement(By.tagName("table"));
//    try {
//      Thread.sleep(1000);
//    }catch(InterruptedException e){
//      e.printStackTrace();
//    }
//    int numCols0 = table0.findElements(By.tagName("th")).size();
//    assertEquals(0, numCols0);
//
//    // Click Load Data
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("horoscopes");
//
//    WebElement table = driver.findElement(By.tagName("table"));
//    try {
//      Thread.sleep(1000);
//    }catch(InterruptedException e){
//      e.printStackTrace();
//    }
//    int numRows = table.findElements(By.tagName("tr")).size();
//    int numCols = table.findElements(By.tagName("th")).size();
//    assertEquals(13, numRows);
//    assertEquals(2, numCols);
//  }

//  @Test
//  public void modificationsToTable() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.id("loadButton"));
//
//    // Click Load Data (first option automatically appears)
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("horoscopes");
//
//    WebElement table = driver.findElement(By.tagName("table"));
//    try {
//      Thread.sleep(1000);
//    }catch(InterruptedException e){
//      e.printStackTrace();
//    }
//    int numRows = table.findElements(By.tagName("tr")).size();
//    int numCols = table.findElements(By.tagName("th")).size();
//    assertEquals(13, numRows);
//    assertEquals(2, numCols);
//
//    WebElement insertButton = driver.findElement(By.id("insert"));
//    WebElement deleteButton = driver.findElement(By.id("delete"));
//    WebElement updateButton = driver.findElement(By.id("update"));
//
//    List<WebElement> textboxes = driver.findElements(By.tagName("input"));
//
//    textboxes.get(0).sendKeys("13");
//    textboxes.get(1).sendKeys("Dog");
//    insertButton.click();
//
//    WebElement table1 = driver.findElement(By.tagName("table"));
//    try {
//      Thread.sleep(1000);
//    } catch(InterruptedException e){
//      e.printStackTrace();
//    }
//    int numRows1 = table1.findElements(By.tagName("tr")).size();
//    int numCols1 = table1.findElements(By.tagName("th")).size();
//    assertEquals(14, numRows1);
//    assertEquals(2, numCols1);
//
//    textboxes.get(4).sendKeys("horoscope_id");
//    textboxes.get(5).sendKeys("13");
//    textboxes.get(6).sendKeys("horoscope");
//    textboxes.get(7).sendKeys("Cat");
//
//    updateButton.click();
//
//    textboxes.get(2).sendKeys("horoscope");
//    textboxes.get(3).sendKeys("Cat");
//
//    deleteButton.click();
//
//    WebElement table2 = driver.findElement(By.tagName("table"));
//    try {
//      Thread.sleep(1000);
//    }catch(InterruptedException e){
//      e.printStackTrace();
//    }
//    int numRows2 = table2.findElements(By.tagName("tr")).size();
//    int numCols2 = table2.findElements(By.tagName("th")).size();
//    assertEquals(13, numRows2);
//    assertEquals(2, numCols2);
//  }
//
//
///**
//   * Helper method to get table.
//   */
//
//  public List<List<String>> getTable() {
//    List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
//    List<List<String>> t = new ArrayList<>();
//
//    for (WebElement r : tableRows) {
//      List<String> newRow = new ArrayList<>();
//      for (WebElement cell : r.findElements(By.tagName("td"))) {
//        newRow.add(cell.getText());
//      }
//      if (!newRow.isEmpty()) {
//        t.add(newRow);
//      }
//    }
//    return t;
//  }
//
//
///**
//   * Helper method to sort numbers in descending order.
//   */
//
//  public boolean isSortedByDescendingNum(List<List<String>> table){
//    int lastRowEntry = 200;
//    for (List<String> row: table) {
//      if (!(Integer.parseInt(row.get(0)) <= lastRowEntry)){
//        return false;
//      }
//      lastRowEntry = Integer.parseInt(row.get(0));
//    }
//    return true;
//  }
//
///**
//   * Helper method to sort numbers in ascending order.
//   */
//
//  public boolean isSortedByAscendingNum (List<List<String>> table) {
//    int lastRowEntry = 0;
//    for (List<String> row: table) {
//      if (!(Integer.parseInt(row.get(0)) >= lastRowEntry)){
//        return false;
//      }
//      lastRowEntry = Integer.parseInt(row.get(0));
//    }
//    return true;
//  }
//
//
///**
//   * Helper method to sort strings in descending order.
//   */
//
//  public boolean isSortedByDescendingString (List<List<String>> table) {
//    String lastRowEntry = "Z";
//    for (List<String> row: table) {
//      System.out.println(row.get(1));
//      if (!(row.get(1).compareTo(lastRowEntry) <= 0)){
//        return false;
//      }
//      lastRowEntry = row.get(1);
//    }
//    return true;
//  }
//
//
///**
//   * Helper method to sort strings in ascending order.
//   */
//
//  public boolean isSortedByAscendingString (List<List<String>> table) {
//    String lastRowEntry = "";
//    for (List<String> row: table) {
//      System.out.println(row.get(1));
//      if (!(row.get(1).compareTo(lastRowEntry) >= 0)){
//        return false;
//      }
//      lastRowEntry = row.get(1);
//    }
//    return true;
//  }
//
//  @Test
//  public void numericalSortingOfTable() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.id("loadButton"));
//
//    // Click Load Data
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("horoscopes");
//
//    Select dropdown1 = new Select(driver.findElement(By.id("dropdownSortBy")));
//    dropdown1.selectByValue("horoscope_id");
//
//    Select dropdown2 = new Select(driver.findElement(By.id("dropdownSortOrder")));
//    dropdown2.selectByValue("Descending");
//    WebElement sortButton = driver.findElement(By.id("sortButton"));
//    sortButton.click();
//
//    List<List<String>> descendingTable = new ArrayList<>();
//
//    while (descendingTable.equals(new ArrayList<>())) {
//      try {
//        descendingTable = getTable();
//      } catch (StaleElementReferenceException e) {
//        System.out.println("Failed to get table.");
//      }
//    }
//
//    assertTrue(isSortedByDescendingNum(descendingTable));
//
//    dropdown2.selectByValue("Ascending");
//    sortButton.click();
//    List<List<String>> ascendingTable = new ArrayList<>();
//
//    while (ascendingTable.equals(new ArrayList<>())) {
//      try {
//        ascendingTable = getTable();
//      } catch (StaleElementReferenceException e) {
//        System.out.println("Failed to get table.");
//      }
//    }
//    assertTrue(isSortedByAscendingNum(ascendingTable));
//
//    Collections.reverse(descendingTable);
//    assertEquals(ascendingTable, descendingTable);
//  }
//
//  @Test
//  public void stringSortingOfTable() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.id("loadButton"));
//
//    // Click Load Data
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("horoscopes");
//
//    Select dropdown1 = new Select(driver.findElement(By.id("dropdownSortBy")));
//    dropdown1.selectByValue("horoscope");
//
//    Select dropdown2 = new Select(driver.findElement(By.id("dropdownSortOrder")));
//    dropdown2.selectByValue("Descending");
//    WebElement sortButton = driver.findElement(By.id("sortButton"));
//    sortButton.click();
//
//    List<List<String>> descendingTable = new ArrayList<>();
//
//    while (descendingTable.equals(new ArrayList<>())) {
//      try {
//        descendingTable = getTable();
//      } catch (StaleElementReferenceException e) {
//        System.out.println("Failed to get table.");
//      }
//    }
//    assertTrue(isSortedByDescendingString(descendingTable));
//
//    dropdown2.selectByValue("Ascending");
//    sortButton.click();
//    List<List<String>> ascendingTable = new ArrayList<>();
//
//    while (ascendingTable.equals(new ArrayList<>())) {
//      try {
//        ascendingTable = getTable();
//      } catch (StaleElementReferenceException e) {
//        System.out.println("Failed to get table.");
//      }
//    }
//    assertTrue(isSortedByAscendingString(ascendingTable));
//  }
//
//  @Test
//  public void checkHeaderNamesMatch() {
//    driver.get("http://www.localhost:3000/");
//
//    WebElement loader = driver.findElement(By.tagName("button"));
//    // Click Load Data (first option (sqlite_sequence) automatically appears)
//    loader.click();
//
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("tas");
//
//    WebElement table = driver.findElement(By.tagName("table"));
//
//    List<String> headerStrings = new ArrayList<>();
//    List<WebElement> headers = table.findElements(By.tagName("th"));
//
//    for (WebElement element: headers) {
//      headerStrings.add(element.getText());
//    }
//    assert(headerStrings.size() == 3);
//    assert(headerStrings.get(0).equals("id"));
//    assert(headerStrings.get(1).equals("name"));
//    assert(headerStrings.get(2).equals("role"));
//
//    dropdown.selectByValue("horoscopes");
//    headers = table.findElements(By.tagName("th"));
//    headerStrings = new ArrayList<>();
//    for (WebElement element: headers) {
//      headerStrings.add(element.getText());
//    }
//    assert(headerStrings.size() == 2);
//    assert(headerStrings.get(0).equals("horoscope_id"));
//    assert(headerStrings.get(1).equals("horoscope"));
//
//    dropdown.selectByValue("ta_horoscope");
//    headers = table.findElements(By.tagName("th"));
//    headerStrings = new ArrayList<>();
//    for (WebElement element: headers) {
//      headerStrings.add(element.getText());
//    }
//    assert(headerStrings.size() == 2);
//    assert(headerStrings.get(0).equals("ta_id"));
//    assert(headerStrings.get(1).equals("horoscope_id"));
//  }
//
//  @Test
//  public void clearAfterRefresh() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.tagName("button"));
//    // Click Load Data
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("tas");
//
//    WebElement table = driver.findElement(By.tagName("table"));
//    String contentAfterLoad = table.getAttribute("innerHTML");
//    assert(contentAfterLoad.length() > 0);
//    driver.navigate().refresh();
//    table = driver.findElement(By.tagName("table"));
//    String contentAfterRefresh = table.getAttribute("innerHTML");
//    assert(!contentAfterLoad.equals(contentAfterRefresh));
//  }
//
//  @Test
//  public void checkConsistentColumnCount() {
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.tagName("button"));
//    // Click Load Data (first option (sqlite_sequence) automatically appears)
//    loader.click();
//    Select dropdown0 = new Select(driver.findElement(By.id("dropdown")));
//    dropdown0.selectByValue("sqlite_sequence");
//
//    WebElement table = driver.findElement(By.tagName("table"));
//    List<WebElement> entries = table.findElements(By.tagName("tbody"));
//    // Remove header entry
//    entries.remove(0);
//
//    List<WebElement> headers = table.findElements(By.tagName("th"));
//    int columnSize = headers.size();
//    for (WebElement entry: entries) {
//      List<WebElement> cells = entry.findElements(By.tagName("td"));
//      assert(cells.size() == columnSize);
//    }
//    // Repeat for different table
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//    dropdown.selectByValue("horoscopes");
//    entries = table.findElements(By.tagName("tbody"));
//    // Remove header entry
//    entries.remove(0);
//    headers = table.findElements(By.tagName("th"));
//    columnSize = headers.size();
//    for (WebElement entry: entries) {
//      List<WebElement> cells = entry.findElements(By.tagName("td"));
//      assert(cells.size() == columnSize);
//    }
//  }

//  @Test
//  public void noContentChangeForSameSelection(){
//    driver.get("http://www.localhost:3000/");
//    WebElement loader = driver.findElement(By.tagName("button"));
//    WebElement table = driver.findElement(By.tagName("table"));
//    // Click Load Data (first option (sqlite_sequence) automatically appears)
//    loader.click();
//    Select dropdown = new Select(driver.findElement(By.id("dropdown")));
//
//    dropdown.selectByValue("ta_horoscope");
//    String content = table.getAttribute("innerHTML");
//    dropdown.selectByValue("ta_horoscope");
//    String newContent = table.getAttribute("innerHTML");
//    assert(content.equals(newContent));
//  }
//
//
///**
//      * Remove references created by a test, reset to old state, etc., connection is terminated so website
//      * is no longer interactive.
//   **/
//
//  @After
//  public void tearDown() {
//    Spark.stop();
//    driver.quit();
//  }
//}
