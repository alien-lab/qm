package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.repository.MenuRepository;
import com.alienlab.niit.qm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<TbMenuEntity> getAllMenus() throws Exception {
        return menuRepository.findAll();
    }

    @Override
    public TbMenuEntity saveMenu(TbMenuEntity tbMenuEntity) throws Exception {
        if (tbMenuEntity != null){
            return  menuRepository.save(tbMenuEntity);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteMenu(long id) throws Exception {
        try {
            menuRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TbMenuEntity getMenuById(long menu_id) throws Exception {
        return menuRepository.findOne(menu_id);
    }
}
