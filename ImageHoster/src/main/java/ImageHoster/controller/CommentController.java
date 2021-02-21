package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;
    //Method used to create Comment for corresponding Image
    //Once comment entered and submitted it is then added with
    //other comments below the image
    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    private String createComment(@RequestParam("comment") String commentBody, Comment comment,
                                 @PathVariable("imageId") Integer imageId,
                                 HttpSession session){
        comment.setCreatedDate(LocalDate.now());
        comment.setText(commentBody);
        Image image = imageService.getImage(imageId);
        comment.setImage(image);
        comment.setUser((User)session.getAttribute("loggeduser"));
        commentService.createComment(comment);
        return "redirect:/images/"+imageId+"/"+image.getTitle();
    }
}

