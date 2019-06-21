package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysOptionsDO;
import net.laoyeye.yyms.repository.SysOptionsRepository;
import net.laoyeye.yyms.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author laoyeye.net
 * @Description: 系统配置
 * @date 2019/6/21 20:52
 */
@Service
public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private SysOptionsRepository sysOptionsRepository;

    @Override
    public Result save(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Optional<SysOptionsDO> option = sysOptionsRepository.findByOptionCode(entry.getKey());
            SysOptionsDO optionsDO = option.orElse(SysOptionsDO.builder().build());
            SysOptionsDO newOptionsDO = optionsDO.toBuilder()
                    .optionCode(entry.getKey())
                    .optionValue(entry.getValue())
                    .build();
            sysOptionsRepository.save(newOptionsDO);
        }
        return Result.ok("保存成功！");
    }
}
