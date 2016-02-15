// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.mapping;

import de.bytefish.sqlmapper.handlers.IValueHandler;

import java.sql.ResultSet;
import java.util.function.BiConsumer;

public class PropertyMapping<TEntity, TProperty> implements IPropertyMapping<TEntity> {

    private String columnName;
    private BiConsumer<TEntity, TProperty> setter;
    private IValueHandler<TProperty> handler;


    public PropertyMapping(String columnName, BiConsumer<TEntity, TProperty> setter, IValueHandler<TProperty> handler) {
        this.columnName = columnName;
        this.setter = setter;
        this.handler = handler;
    }

    public void map(final TEntity entity, final ResultSet resultSet) {
        TProperty value = handler.handle(columnName, resultSet);

        setter.accept(entity, value);
    }
}
