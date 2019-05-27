import { AppPage } from './app.po';
import { browser, by,element,logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    browser.driver.sleep(2000);
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MusixApplication');
  });

  
  it('should be able to navigate to Register page', () => {
    browser.driver.sleep(2000);
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(2000);
  });

  
  it('should be able to register user', () => {
    browser.driver.sleep(2000);
    browser.element(by.id("userName")).sendKeys("musix1");
    browser.element(by.id("email")).sendKeys("musix@gmail.com");
    browser.element(by.id("password")).sendKeys("musix1");
    browser.element(by.css('.register-user')).click();
    browser.driver.sleep(2000);
  });

  it('should be able to login user', () => {
    browser.driver.sleep(2000);
    browser.element(by.id("userName")).sendKeys("musix1");
    browser.element(by.id("password")).sendKeys("musix1");
    browser.element(by.css('.login-user')).click();
    browser.driver.sleep(2000);
  });



  it('shouldbe able to click Menu item for Inda', () => {
    browser.driver.sleep(2000);
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-india')).click();
    expect(browser.getCurrentUrl()).toContain('/India');
    browser.driver.sleep(1000);
  });

  it('shouldbe able to save track  for Inda', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks=element.all(by.css('tracks-card'));
    browser.driver.sleep(1000);
    browser.element(by.css('.addButton')).click();
    browser.driver.sleep(1000);
    });

    it('shouldbe able to click Menu item for Spain', () => {
      browser.element(by.css('.mat-button')).click();
      browser.driver.sleep(1000);
      browser.element(by.css('.mat-menu-item-spain')).click();
      expect(browser.getCurrentUrl()).toContain('/Spain');
      browser.driver.sleep(1000);
    });
  
    it('shouldbe able to save track  for Spain', () => {
      browser.driver.manage().window().maximize();
      browser.driver.sleep(1000);
      const tracks=element.all(by.css('.tracks-card'));
      browser.driver.sleep(1000);
      browser.element(by.css('.addButton')).click();
      browser.driver.sleep(1000);
      });

      it("should be able to get all the tracks",()=>{
        browser.driver.sleep(1000);
        browser.element(by.css(".mat-button-wishlist")).click();
        expect(browser.getCurrentUrl()).toContain("/WishList");
        browser.driver.sleep(1000);
      });
      
      it("should be able to delete track from wishList",()=>{
        browser.driver.sleep(1000);
        const tracks = element.all(by.css(".tracks-card"));
        browser.driver.sleep(1000);
        browser.element(by.css(".deleteButton")).click();
        browser.driver.sleep(1000);
      });
      
      it("should be able to update comments",()=>{
        browser.driver.sleep(1000);
        const tracks = element.all(by.css("tracks-card"));
        browser.driver.sleep(1000);
        browser.element(by.css(".updateButton")).click();
        browser.driver.sleep(1000);
      });

      it("should be able to save comments",() => {
        browser.driver.sleep(500);
        browser.element(by.css(".matInput")).sendKeys("new Comments");
        browser.driver.sleep(500);
        browser.element(by.css(".updateComments")).click();
        browser.driver.sleep(500);
    });

    it("should be able to logout",()=>{
      browser.driver.sleep(1000);
      browser.element(by.css(".mat-button-logout")).click();
      browser.driver.sleep(1000);
    });
  
});
