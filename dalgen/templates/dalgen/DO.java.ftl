<@pp.dropOutputFile />
<#list dalgen.dos as DO>
    <@pp.changeOutputFile name = "/main/java/${DO.classPath}/${DO.className}.java" />
    package ${DO.packageName};

    <#list DO.importLists as import>
        import ${import};
    </#list>
    import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
    import org.apache.commons.lang3.builder.ToStringStyle;

    /**
    * The table ${DO.desc}
    */
    public class ${DO.className}{

    <#list DO.fieldses as fields>
        /**
        * ${fields.name} ${fields.desc}.
        */
        private ${fields.javaType} ${fields.name};
    </#list>
    <#list DO.fieldses as fields>

        /**
        * Set ${fields.name} ${fields.desc}.
        */
        public void set${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        this.${fields.name} = ${fields.name};
        }

        /**
        * Get ${fields.name} ${fields.desc}.
        *
        * @return the string
        */
        public ${fields.javaType} get${fields.name?cap_first}(){
        return ${fields.name};
        }
    </#list>

    @Override
    public String toString() {
    return ReflectionToStringBuilder.reflectionToString(this,
    ToStringStyle.SHORT_PREFIX_STYLE);
    }
    }
</#list>