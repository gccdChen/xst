var ioc = {
	dataSource : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		fields : {
//			driverClassName : 'org.h2.Driver',
//			url : 'jdbc:h2:scaubook'
//         url : "jdbc:postgresql://localhost:5432/mydatabase",
//         username : "enzozhong",
//         password : "123"
			driverClassName : 'com.mysql.jdbc.Driver',
			url : 'jdbc:mysql://localhost:3306/zzzd',
			username : "root",
			password:"110110"
		},
		events : {
            depose : 'close'
        }
	},
	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [{refer:'dataSource'}]
	}
}