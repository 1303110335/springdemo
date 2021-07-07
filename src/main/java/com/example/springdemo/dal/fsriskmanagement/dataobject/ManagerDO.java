    package com.example.springdemo.dal.fsriskmanagement.dataobject;

        import java.util.Date;
        import com.example.springdemo.dal.fsriskmanagement.dataobject.ManagerDO;
    import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
    import org.apache.commons.lang3.builder.ToStringStyle;
        import org.hibernate.validator.constraints.Length;

    /**
    * The table 风控中心管理员表
    */
    public class ManagerDO{

        /**
        * id ID.
        */
        private Integer id;
        /**
        * token 登录 token.
        */
        private String token;
        /**
        * password 登录密码.
        */
        private String password;
        /**
        * username 管理员用户名.
        */
        private String username;
        /**
        * managerId 管理员 id.
        */
        private String managerId;
        /**
        * createTime 创建时间.
        */
        private Date createTime;
        /**
        * updateTime 修改时间.
        */
        private Date updateTime;

        /**
        * Set id ID.
        */
        public void setId(Integer id){
        this.id = id;
        }

        /**
        * Get id ID.
        *
        * @return the string
        */
        public Integer getId(){
        return id;
        }

        /**
        * Set token 登录 token.
        */
        public void setToken(String token){
        this.token = token;
        }

        /**
        * Get token 登录 token.
        *
        * @return the string
        */
        public String getToken(){
        return token;
        }

        /**
        * Set password 登录密码.
        */
        public void setPassword(String password){
        this.password = password;
        }

        /**
        * Get password 登录密码.
        *
        * @return the string
        */
        public String getPassword(){
        return password;
        }

        /**
        * Set username 管理员用户名.
        */
        public void setUsername(String username){
        this.username = username;
        }

        /**
        * Get username 管理员用户名.
        *
        * @return the string
        */
        public String getUsername(){
        return username;
        }

        /**
        * Set managerId 管理员 id.
        */
        public void setManagerId(String managerId){
        this.managerId = managerId;
        }

        /**
        * Get managerId 管理员 id.
        *
        * @return the string
        */
        public String getManagerId(){
        return managerId;
        }

        /**
        * Set createTime 创建时间.
        */
        public void setCreateTime(Date createTime){
        this.createTime = createTime;
        }

        /**
        * Get createTime 创建时间.
        *
        * @return the string
        */
        public Date getCreateTime(){
        return createTime;
        }

        /**
        * Set updateTime 修改时间.
        */
        public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
        }

        /**
        * Get updateTime 修改时间.
        *
        * @return the string
        */
        public Date getUpdateTime(){
        return updateTime;
        }

    @Override
    public String toString() {
    return ReflectionToStringBuilder.reflectionToString(this,
    ToStringStyle.SHORT_PREFIX_STYLE);
    }
    }
