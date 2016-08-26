package com.github.osvaldopina.signedcontract.enforcer;

/**
 * Created by deinf.osvaldo on 26/08/2016.
 */
public interface Navigator<T, E> {

    E navigate(T e);
}
