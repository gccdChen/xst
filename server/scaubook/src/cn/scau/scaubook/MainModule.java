package cn.scau.scaubook;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(scanPackage=true)
@Ok("json")
@Fail("json")
@IocBy(type=ComboIocProvider.class,
        args={
              "*org.nutz.ioc.loader.json.JsonLoader","ioc/",
              "*org.nutz.ioc.loader.annotation.AnnotationIocLoader","cn.scau.scaubook"
        })
@SetupBy(value=GccdSetup.class)
public class MainModule {

}
