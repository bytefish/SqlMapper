package de.bytefish.sqlmapper;// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.


import de.bytefish.sqlmapper.handlers.IValueHandler;
import de.bytefish.sqlmapper.handlers.IValueHandlerProvider;
import de.bytefish.sqlmapper.handlers.ValueHandlerProvider;
import de.bytefish.sqlmapper.mapping.IPropertyMapping;
import de.bytefish.sqlmapper.mapping.PropertyMapping;
import de.bytefish.sqlmapper.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class ResultSetMapping<TEntity> {

    private IValueHandlerProvider valueHandlerProvider;
    private String schemaName;
    private String tableName;

    private List<IPropertyMapping<TEntity>> mappings;

    public ResultSetMapping() {
        this(new ValueHandlerProvider());
    }

    public ResultSetMapping(IValueHandlerProvider valueHandlerProvider) {
        this.valueHandlerProvider = valueHandlerProvider;

        this.mappings = new ArrayList<>();
    }

    protected <TProperty> void map(String columnName, Class<TProperty> type, BiConsumer<TEntity, TProperty> setter) {
        IValueHandler<TProperty> handler = valueHandlerProvider.resolve(type);

        mappings.add(new PropertyMapping<>(columnName, setter, handler));
    }

    public List<IPropertyMapping<TEntity>> getMappings() {
        return mappings;
    }
}
