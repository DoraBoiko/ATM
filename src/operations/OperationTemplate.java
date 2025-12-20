package operations;

public abstract class OperationTemplate {

    public final void perform() {
        before();
        executeOperation();
        after();
    }

    protected void before() {}

    protected abstract void executeOperation();

    protected void after() {}
}
