//package com.koolsoft.authen.config;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//
////    public interface ModelMapperFactory {
////        ModelMapper getMapper();
////    }
////    private ModelMapperFactory modelMapperFactory() {
////        return () -> {
////            ModelMapper mapper = new ModelMapper();
////            mapper.getConfiguration()
////                    .setMatchingStrategy(MatchingStrategies.STRICT);
//////            mapper.typeMap(A.class, B.class)
//////                    .addMappings(mapping -> {
//////                        mapping.map(Evidence::getViolationImageID, ViolationDTO.Evidence::setImageID);
//////                    });
////            return mapper;
////        };
////    }
////
////    @Bean
////    public ModelMapper modelMapper(){
////        return new ModelMapper();
////    }
//
//}
