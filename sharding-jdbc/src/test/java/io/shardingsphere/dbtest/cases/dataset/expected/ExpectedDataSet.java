/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.dbtest.cases.dataset.expected;

import io.shardingsphere.core.rule.DataNode;
import io.shardingsphere.dbtest.cases.dataset.init.DataSetRow;
import io.shardingsphere.dbtest.cases.dataset.metadata.DataSetMetadata;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Expected data set.
 *
 * @author zhangliang
 */
@Getter
@XmlRootElement(name = "datasets")
public final class ExpectedDataSet {
    
    @XmlElement(name = "metadata")
    private List<DataSetMetadata> metadataList = new LinkedList<>();
    
    @XmlElement(name = "dataset")
    private List<DataSetRow> rows = new LinkedList<>();
    
    /**
     * Find data set metadata via table name.
     * 
     * @param tableName table name
     * @return expected metadata
     */
    public DataSetMetadata findMetadata(final String tableName) {
        for (DataSetMetadata each : metadataList) {
            if (tableName.equals(each.getTableName())) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("Cannot find expected metadata via table name: '%s'", tableName));
    }
    
    /**
     * Find data set rows via data node.
     *
     * @param dataNode data node
     * @return data set rows belong to current data node
     */
    public List<DataSetRow> findDataSetRows(final DataNode dataNode) {
        List<DataSetRow> result = new ArrayList<>(rows.size());
        for (DataSetRow each : rows) {
            if (new DataNode(each.getDataNode()).equals(dataNode)) {
                result.add(each);
            }
        }
        return result;
    }
}
