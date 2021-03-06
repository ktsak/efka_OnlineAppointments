package org.team1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.team1.models.Doctor;
import org.team1.repositories.DoctorRepository;
import org.team1.security.MyDoctorDetails;

@Service
public class MyDoctorDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public MyDoctorDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByUserame(username);
        if (doctor == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyDoctorDetails(doctor);
    }

}