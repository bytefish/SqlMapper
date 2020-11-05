package de.bytefish.sqlmapper;// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.


import de.bytefish.sqlmapper.handlers.IValueHandler;
import de.bytefish.sqlmapper.handlers.IValueHandlerProvider;
import de.bytefish.sqlmapper.handlers.ValueHandlerProvider;
import de.bytefish.sqlmapper.mapping.IPropertyMapping;
import de.bytefish.sqlmapper.mapping.PropertyMapping;
import de.bytefish.sqlmapper.utils.StringUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class AbstractMap<TEntity> {

    private IValueHandlerProvider provider;
    private String schemaName;
    private String tableName;

    private List<IPropertyMapping<TEntity>> mappings;

    public AbstractMap(String tableName) {
        this("", tableName);
    }

    public AbstractMap(String schemaName, String tableName) {
        this(new ValueHandlerProvider(), schemaName, tableName);
    }

    public AbstractMap(IValueHandlerProvider valueHandlerProvider, String schemaName, String tableName) {
        this.provider = valueHandlerProvider;
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.mappings = new ArrayList<>();
    }

    protected <TProperty> void map(String columnName, Class<TProperty> type, BiConsumer<TEntity, TProperty> setter) {
        IValueHandler<TProperty> handler = provider.resolve(type);

        mappings.add(new PropertyMapping<TEntity, TProperty>(columnName, setter, handler));
    }

    public List<IPropertyMapping<TEntity>> getMappings() {
        return mappings;
    }

    public String getFullQualifiedTableName() {
        if (StringUtils.isNullOrWhiteSpace(schemaName)) {
            return tableName;
        }
        return String.format("%1$s.%2$s", schemaName, tableName);
    }
}
