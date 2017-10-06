package com.example.commonapi.commands.product;

/**
 * Created by kellen on 2017/9/5.
 */
public class DecreaseStockCommand extends ChangeStockCommand {

    public DecreaseStockCommand() {
    }

    public DecreaseStockCommand(Long id, int number) {
        super(id, number);
    }
}
