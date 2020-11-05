// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.iterator;

import de.bytefish.sqlmapper.SqlMapper;
import de.bytefish.sqlmapper.result.SqlMappingResult;

import java.sql.ResultSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class ResultSetSpliterator<TEntity> extends Spliterators.AbstractSpliterator<SqlMappingResult<TEntity>> {

    private final ResultSet resultSet;
    private final SqlMapper<TEntity> sqlMapper;

    public ResultSetSpliterator(final SqlMapper<TEntity> sqlMapper, final ResultSet resultSet) {
        super(Long.MAX_VALUE,Spliterator.ORDERED);

        this.sqlMapper = sqlMapper;
        this.resultSet = resultSet;
    }

    @Override
    public boolean tryAdvance(Consumer<? super SqlMappingResult<TEntity>> action) {
        if (next()) {
            action.accept(sqlMapper.toEntity(resultSet));
            return true;
        } else {
            return false;
        }
    }

    private boolean next() {
        try {
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}