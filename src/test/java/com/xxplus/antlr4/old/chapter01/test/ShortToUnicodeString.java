package com.xxplus.antlr4.old.chapter01.test;

import com.xxplus.antlr4.old.chapter01.gen.ArrayInitBaseListener;
import com.xxplus.antlr4.old.chapter01.gen.ArrayInitParser;

/**
 * Created by admin on 2016-03-26.
 */
public class ShortToUnicodeString extends ArrayInitBaseListener{

    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print("'");
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print("'");
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {

        if(ctx.INT() == null){
            System.out.print(ctx.INT());
        }else{
            System.out.printf("%s", Integer.valueOf(ctx.INT().getText()));
        }

    }
}
