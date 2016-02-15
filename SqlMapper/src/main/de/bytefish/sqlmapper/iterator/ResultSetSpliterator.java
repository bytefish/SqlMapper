// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.iterator;

import java.sql.ResultSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class ResultSetSpliterator extends Spliterators.AbstractSpliterator<ResultSet> {

    private final ResultSet rs;

    public ResultSetSpliterator(ResultSet rs) {
        super(Long.MAX_VALUE,Spliterator.ORDERED);
        this.rs = rs;
    }

    @Override public boolean tryAdvance(Consumer<? super ResultSet> action) {
        if (next()) {
            action.accept(rs);
            return true;
        } else {
            return false;
        }
    }

    private boolean next() {
        try {
            rs.next();
        } catch (Exception e) {
            // TODO Throw Exception or simply return false?
            throw new RuntimeException(e);
        }
        return true;
    }
}