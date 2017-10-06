package com.example.commonapi.commands.product;

/**
 * Created by kellen on 2017/9/5.
 */
public class IncreaseStockCommand extends ChangeStockCommand {

    public IncreaseStockCommand() {
    }

    public IncreaseStockCommand(Long id, int number) {
        super(id, number);
    }
}
