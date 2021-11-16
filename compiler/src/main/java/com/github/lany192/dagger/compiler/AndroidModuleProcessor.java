package com.github.lany192.dagger.compiler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.lany192.dagger.annotation.Dagger;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class AndroidModuleProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        set.add(Dagger.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment environment) {
        // 准备在gradle的控制台打印信息
        Messager messager = processingEnv.getMessager();
        List<MethodSpec> methods = new ArrayList<>();
        //自定义注解
        Set<? extends Element> daggerElements = environment.getElementsAnnotatedWith(Dagger.class);
        //兼容ARouter的注解
        Set<? extends Element> routeElements = environment.getElementsAnnotatedWith(Route.class);

        for (Element element : daggerElements) {
            if (element instanceof VariableElement) {
                messager.printMessage(Diagnostic.Kind.WARNING, "忽略注解在非class上的注解");
                return false;
            }
            methods.add(getMethodSpec(messager, element));
        }
        for (Element element : routeElements) {
            if (element instanceof VariableElement) {
                messager.printMessage(Diagnostic.Kind.WARNING, "忽略注解在非class上的注解");
                return false;
            }
            methods.add(getMethodSpec(messager, element));
        }
        //去重
        HashSet<MethodSpec> hashSet = new HashSet<>(methods);
        methods.clear();
        methods.addAll(hashSet);
        try {
            createActivityModule(methods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private MethodSpec getMethodSpec(Messager messager, Element element) {
        //获取包信息
        PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(element);
        String className = element.getSimpleName().toString();
        messager.printMessage(Diagnostic.Kind.NOTE, " 发现目标类: " + packageElement.getQualifiedName() + "." + className);

        return MethodSpec.methodBuilder(Utils.toLowerCaseFirstOne(className))
                .addModifiers(Modifier.ABSTRACT)
                .addAnnotation(AnnotationSpec
                        .builder(ClassName.get("dagger.android", "ContributesAndroidInjector"))
                        .build())
                .returns(ClassName.get(packageElement.getQualifiedName().toString(), className))
                .build();
    }

    private void createActivityModule(List<MethodSpec> methods) throws Exception {
        TypeSpec.Builder builder = TypeSpec.classBuilder("AndroidModule")
                .addJavadoc("自动生成代码，请勿编辑")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addAnnotation(AnnotationSpec
                        .builder(ClassName.get("dagger", "Module"))
                        .build());
        for (MethodSpec method : methods) {
            builder.addMethod(method);
        }
        JavaFile javaFile = JavaFile.builder("com.github.lany192.dagger", builder.build()).build();
        javaFile.writeTo(filer);
    }
}
