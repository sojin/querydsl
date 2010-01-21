/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.hql;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import javax.annotation.Nullable;

import org.hibernate.hql.ast.HqlParser;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.SearchResults;
import com.mysema.query.types.expr.Expr;

class QueryHelper extends HQLQueryBase<QueryHelper> {

    public QueryHelper() {
        super(new DefaultQueryMetadata(), HQLTemplates.DEFAULT);
    }

    public long count() {
        return 0;
    }

    @Override
    public Iterator<Object[]> iterate(Expr<?>[] args) {
        return null;
    }

    @Nullable
    public <RT> Iterator<RT> iterate(Expr<RT> projection) {
        return null;
    }

    @Nullable
    public <RT> SearchResults<RT> listResults(Expr<RT> expr) {
        return null;
    }

    public void parse() throws RecognitionException, TokenStreamException {
        try {
            String input = toString();
            System.out.println("input: " + input.replace('\n', ' '));
            HqlParser parser = HqlParser.getInstance(input);
            parser.setFilter(false);
            parser.statement();
            AST ast = parser.getAST();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            parser.showAst(ast, new PrintStream(baos));
            assertEquals("At least one error occurred during parsing " + input,
                    0, parser.getParseErrorHandler().getErrorCount());
        } finally {
            // clear();
            System.out.println();
        }
    }

    public QueryHelper select(Expr<?>... exprs) {
        queryMixin.addToProjection(exprs);
        return this;
    }
}