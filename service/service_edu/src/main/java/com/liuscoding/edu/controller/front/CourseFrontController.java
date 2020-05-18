package com.liuscoding.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuscoding.commonutils.JwtUtils;
import com.liuscoding.commonutils.model.vo.CourseOrderVo;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.client.OrdersClient;
import com.liuscoding.edu.entity.Course;
import com.liuscoding.edu.model.query.CourseQuery;
import com.liuscoding.edu.model.vo.ChapterVo;
import com.liuscoding.edu.model.vo.CourseWebVo;
import com.liuscoding.edu.service.ChapterService;
import com.liuscoding.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @className: CourseFrontController
 * @description: 课程C端Controller
 * @author: liusCoding
 * @create: 2020-05-17 11:20
 */

@Api(tags = "课程C端服务")
@RestController
@RequestMapping("/edu/courseFront")
@CrossOrigin
public class CourseFrontController {

    private final CourseService courseService;

    private final ChapterService chapterService;

    private final OrdersClient ordersClient;

    public CourseFrontController(CourseService courseService, ChapterService chapterService, OrdersClient ordersClient) {
        this.courseService = courseService;
        this.chapterService = chapterService;
        this.ordersClient = ordersClient;
    }

    @ApiOperation("条件分页查询课程")
    @PostMapping("/getFrontCourse/{page}/{size}")
    public ResultVo getFrontCourseList(@PathVariable long page,@PathVariable long size,
         CourseQuery courseQuery
    ){
        Page<Course> coursePage = new Page<>(page,size);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseQuery);
        return ResultVo.ok().data(map);
    }

    @ApiOperation("根据课程id，查询课程信息")
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public ResultVo getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id，查询课程的基本信息
        CourseWebVo courseWebVo =  courseService.getBaseCourseInfo(courseId);

        //根据课程id 和会员id 查询当前课程是否已经支付过了
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        Boolean buyCourse =false;
        if(StringUtils.isNotBlank(memberId)){
            buyCourse = ordersClient.isBuyCourse(courseId, memberId);
        }
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return ResultVo.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList).data("isBuy",buyCourse);
    }


    @GetMapping("/getCourseInfoOrder/{id}")
    @ApiOperation(("根据课程id查询课程基本信息（远程调用）"))
    public CourseOrderVo getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(id);
        CourseOrderVo courseOrderVo = new CourseOrderVo();
        BeanUtils.copyProperties(courseWebVo,courseOrderVo);
        return courseOrderVo;
    }
}
