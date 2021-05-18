package com.laoh.demo.service.impl;

import com.laoh.demo.entity.TalentMaterial;
import com.laoh.demo.dao.TalentMaterialDao;
import com.laoh.demo.service.TalentMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 天赋材料表(TalentMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:40:08
 */
@Service("talentMaterialService")
public class TalentMaterialServiceImpl implements TalentMaterialService {
    @Resource
    private TalentMaterialDao talentMaterialDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TalentMaterial queryById(Long id) {
        return this.talentMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TalentMaterial> queryAllByLimit(int offset, int limit) {
        return this.talentMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param talentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public TalentMaterial insert(TalentMaterial talentMaterial) {
        this.talentMaterialDao.insert(talentMaterial);
        return talentMaterial;
    }

    /**
     * 修改数据
     *
     * @param talentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public TalentMaterial update(TalentMaterial talentMaterial) {
        this.talentMaterialDao.update(talentMaterial);
        return this.queryById(talentMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.talentMaterialDao.deleteById(id) > 0;
    }
}
