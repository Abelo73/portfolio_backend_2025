package org.act.portfolio_backend_2025.controller;


import com.cloudinary.utils.ObjectUtils;

import org.act.portfolio_backend_2025.model.Testimonial;
import org.act.portfolio_backend_2025.repository.TestimonialRepository;
import org.act.portfolio_backend_2025.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private TestimonialRepository testimonialRepository;

    @PostMapping
    public Testimonial uploadTestimonial(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            @RequestParam("quote") String quote,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("github") String github
    ) throws IOException {
        // ✅ Upload image to Cloudinary via your service
        String imageUrl = cloudinaryService.uploadImage(image);

        // ✅ Save testimonial with imageUrl
        Testimonial testimonial = new Testimonial();
        testimonial.setName(name);
        testimonial.setRole(role);
        testimonial.setQuote(quote);
        testimonial.setLinkedin(linkedin);
        testimonial.setGithub(github);
        testimonial.setImage(imageUrl);  // ✅ Save Cloudinary URL

        return testimonialRepository.save(testimonial);
    }

    @GetMapping
    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll();
    }

}
