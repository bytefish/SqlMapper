// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper;

import de.bytefish.sqlmapper.functional.IObjectCreator;
import de.bytefish.sqlmapper.iterator.ResultSetSpliterator;
import de.bytefish.sqlmapper.mapping.IPropertyMapping;
import de.bytefish.sqlmapper.result.SqlMappingError;
import de.bytefish.sqlmapper.result.SqlMappingResult;

import java.sql.ResultSet;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class SqlMapper<TEntity> {
    
    private final IObjectCreator<TEntity> creator;
    private final AbstractMap<TEntity> mapping;

    public SqlMapper(IObjectCreator<TEntity> creator, AbstractMap mapping) {
        this.creator = creator;
        this.mapping = mapping;
    }

    public SqlMappingResult<TEntity> toEntity(ResultSet resultSet) {
        TEntity entity = creator.create();

        for (IPropertyMapping<TEntity> propertyMapping : mapping.getMappings()) {
            try {
                propertyMapping.map(entity, resultSet);
            } catch(Exception e) {
                return new SqlMappingResult<TEntity>(new SqlMappingError(e));
            }
        }

        return new SqlMappingResult<TEntity>(entity);
    }

    public Stream<SqlMappingResult<TEntity>> toStream(ResultSet resultSet) {
        return StreamSupport.stream(new ResultSetSpliterator<>(this, resultSet), false);
    }
}
