package com.jesen.andokhttptalk.chain;

public class Task3 extends BaseTask{

    public Task3(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        // // 执行 子节点 链条断
        System.out.println("Task1 任务节点三 执行了");
    }
}
