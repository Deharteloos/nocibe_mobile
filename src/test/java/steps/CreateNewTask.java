package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CreateTaskPage;
import pageObjects.TasksListPage;

public class CreateNewTask {

    private TasksListPage tasksListPage;
    private CreateTaskPage createTaskPage;

    public CreateNewTask(TasksListPage tasksListPage, CreateTaskPage createTaskPage) {
        this.tasksListPage = tasksListPage;
        this.createTaskPage = createTaskPage;
    }

    @Given("Click Add new Task")
    public void clickAddNewTask() {
        tasksListPage.clickAddTaskBtn();
    }

    @Given("Enter TaskName")
    public void enterTaskName() {
        createTaskPage.enterTaskName("Finish Appium Course");
    }

    @Given("Enter TaskDesc")
    public void enterTaskDesc() {
        createTaskPage.enterTaskDesc("Finishing my course ASAP");
    }

    @When("Click Save")
    public void clickSave() {
        createTaskPage.clickSaveBtn();
    }

    @Then("Task added successfully")
    public void taskAddedSuccessfully() {

    }
}
