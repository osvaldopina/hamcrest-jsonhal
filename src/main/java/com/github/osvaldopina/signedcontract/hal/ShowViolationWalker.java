package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.Walker;
import com.github.osvaldopina.signedcontract.hal.link.LinkClause;

import java.util.Arrays;

public class ShowViolationWalker extends Walker {

    private int level = 0;

    public void beforeWalk(ResourceClause resourceClause) {
        level ++;
    }

    public void walk(ResourceClause resourceClause) {
        System.out.println(levelPad() + "resource");
    }

    public void afterWalk(ResourceClause resourceClause) {
        level --;
    }

    public void beforeWalk(LinksClause linksClause) {
        level ++;
    }

    public void walk(LinksClause linksClause) {
        System.out.println(levelPad() + "links");
    }

    public void afterWalk(LinksClause linksClause) {
        level --;
    }

    public void beforeWalk(LinkClause linkClause) {
        level ++;
    }

    public void walk(LinkClause linkClause) {
        if (! linkClause.getViolations().isEmpty()) {
            for(String violation:linkClause.getViolations()) {
                System.out.println(levelPad() + "#" +violation);
            }
        }
        else {
            System.out.println(levelPad() + linkClause.getRel());
        }
    }

    public void afterWalk(LinkClause linkClause) {
        level --;
    }

    private String levelPad() {
        char[] charArray = new char[level*2];
        Arrays.fill(charArray, ' ');
        return new String(charArray);
    }

}
