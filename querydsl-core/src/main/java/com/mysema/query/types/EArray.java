/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types;

import javax.annotation.Nonnegative;

/**
 * @author tiwe
 *
 * @param <EA>
 */
public interface EArray<E> {
    
    /**
     * @return
     */
    ENumber<Integer> size();
    
    /**
     * @param index
     * @return
     */
    Expr<E> get(Expr<Integer> index);

    /**
     * @param index
     * @return
     */
    Expr<E> get(@Nonnegative int index);

}