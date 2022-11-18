package com.seo.w2.service;

import com.seo.w2.dao.TodoDAO;
import com.seo.w2.domain.TodoVO;
import com.seo.w2.dto.TodoDTO;
import com.seo.w2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); //dto -> vo
        log.info(todoVO);
        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = dao.selectAll();

        log.info("voList..............");
        log.info(voList);

        List<TodoDTO> todoDTOList = voList.stream().map(vo->
            modelMapper.map(vo, TodoDTO.class)
        ).collect(Collectors.toList());

        return todoDTOList;

    }

    public TodoDTO get(Long tno) throws Exception{
        log.info("tno: " + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    public void remove(Long tno) throws Exception {
        log.info("tno: " + tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO : " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }

}













