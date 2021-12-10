package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreateTaskPage extends Page {

    public CreateTaskPage() {
    }

    @AndroidFindBy(id = "editTextTitre")
    MobileElement taskNameTxt;

    @AndroidFindBy(id = "editTextNote")
    MobileElement taskDescTxt;

    @AndroidFindBy(id = "action_save")
    MobileElement saveBtn;

    public void enterTaskName(String taskName) {
        clear(taskNameTxt);
        set(taskNameTxt, taskName);
    }

    public void enterTaskDesc(String descText) {
        clear(taskDescTxt);
        set(taskDescTxt, descText);
    }

    public void clickSaveBtn() {
        click(saveBtn);
    }

}
