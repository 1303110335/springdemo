<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#list dalgen.tables as table>
    <@pp.changeOutputFile name = "/${dalgen.tablesPath}/${table.sqlName}.xml" />
<!DOCTYPE table SYSTEM "../config/table-config-1.0.dtd">
    <table sqlname="${table.sqlName}" physicalName="${table.physicalName}"<#if table.remark??>
    remark="${table.remark!}"</#if>>
        <!--    &lt;&gt;   <> -->
        <operation name="insert" paramtype="object" remark="insert:${table.sqlName}">
            INSERT INTO ${table.sqlName}
            <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list table.columnList as column>
        <if test="${column.javaName} != null">`${column.sqlName}`,</if>
    </#list>
            </trim>
            VALUES
            <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list table.columnList as column>
        <if test="${column.javaName} != null">${lib.insertVal(column)},</if>
    </#list>
            </trim>
        </operation>
    </table>
</#list>