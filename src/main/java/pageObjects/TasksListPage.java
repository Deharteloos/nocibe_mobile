package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TasksListPage extends Page {

    public TasksListPage() {

    }

    @AndroidFindBy(id = "fab")
    MobileElement addTaskBtn;

    public void clickAddTaskBtn() {
        click(addTaskBtn);
    }

}
