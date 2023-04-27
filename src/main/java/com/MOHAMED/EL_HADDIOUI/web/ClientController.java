package com.MOHAMED.EL_HADDIOUI.web;


import com.MOHAMED.EL_HADDIOUI.Service.AbonnementService;
import com.MOHAMED.EL_HADDIOUI.Service.ClientService;
import com.MOHAMED.EL_HADDIOUI.entities.Abonnement;
import com.MOHAMED.EL_HADDIOUI.entities.Client;
import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import com.MOHAMED.EL_HADDIOUI.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;

@Controller
@AllArgsConstructor
public class ClientController {
 private ClientService clientService ;
 private AbonnementService abonnementService ;
 private AppUserRepository appUserRepository;
//Page web index
@GetMapping("/")
public String home (){

 return "redirect:/user/index";
}
//Page web index
 @GetMapping(path = "/user/index")
 @PreAuthorize("hasRole('ROLE_USER')")
 public String clients (Model model,
                        @RequestParam(name="page",defaultValue="0") int page,
                        @RequestParam(name="size",defaultValue = "5") int size ,
                        @RequestParam(name="keyword",defaultValue ="") String keyword, Principal principal) {
  String username = principal.getName();
  AppUser appUser =appUserRepository.findByUsername(username);
  if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
   Page<Client> clients = clientService.findByNomContains(keyword, PageRequest.of(page, size));
   model.addAttribute("listClients", clients.getContent());
   model.addAttribute("pages", new int[clients.getTotalPages()]);
   model.addAttribute("currentPage", page);
   model.addAttribute("keyword", keyword);
   model.addAttribute("user",appUser);
   return "clients";
  }
  if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
       model.addAttribute("user",appUser);
   return "index2";
  }
  return "redirect:/notAuthorized";
 }
 //Supprimer une client
 @GetMapping("/admin/delete")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String delete (Long id,String keyword,int page){
  clientService.delete(id);
  return "redirect:/user/index?page="+page+"&keyword="+keyword;
 }
 //Creer une client
 @GetMapping("/admin/formClients")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String formClient(Model model){
  model.addAttribute("client",new Client());
  return "formClients";
}
 //Save une client
 @PostMapping("/admin/save")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String save(Model model,@RequestParam(defaultValue ="") String user, Client client,@RequestParam(defaultValue ="0") int page, @RequestParam(defaultValue ="") String keyword)
 {
 if(user!="")
 {
  AppUser appUser =appUserRepository.findByUsername(user);
  client.setAppUser(appUser);
 }
 clientService.save(client);
  return "redirect:/user/index?page="+page+"&keyword="+keyword;
}
//Modifier une client
 @GetMapping("/admin/editClient")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String editClient(Model model,Long id,String keyword,int page) throws Exception
 {
  Client client=clientService.findClient(id).orElse(null);
  if(client==null) throw new Exception("Client introuvable");
  String user =client.getAppUser().getUsername();
  model.addAttribute("user",user);
  model.addAttribute("client",client);
  model.addAttribute("keyword",keyword);
  model.addAttribute("page",page);
  return "editClient";
}
 //Supprimer  abonnement
 @GetMapping("/admin/deleteAbonnement")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String deleteAbonnement (Long id){
  abonnementService.delete(id);
  return "redirect:/user/index";
 }
 //Modifier  abonnement
 @GetMapping("/user/formAbonnements")
 @PreAuthorize("hasRole('ROLE_USER')")
 public String formAbonnements(Model model,Principal principal){
  String username = principal.getName();
  AppUser appUser =appUserRepository.findByUsername(username);
  model.addAttribute("client",appUser.getClient());
  Abonnement abonnement = new Abonnement();
  abonnement.setClient(appUser.getClient());
  model.addAttribute("abonnement",abonnement);
  return "formAbonnement";
}
 //save abonnement
 @PostMapping("/user/saveAbonnement")
 @PreAuthorize("hasRole('ROLE_USER')")
 public String saveAbonnement(@ModelAttribute Abonnement abonnement,Principal principal) {
  String username = principal.getName();
  AppUser appUser =appUserRepository.findByUsername(username);
  abonnement.setClient(appUser.getClient());
  abonnementService.save(abonnement);
  return "redirect:/user/index";
 }
 @GetMapping("/user/editAbonnement")
 @PreAuthorize("hasRole('ROLE_USER')")
 public String editAbonnement(Model model, @RequestParam Long id) {
  Abonnement abonnement = abonnementService.findAbonnement(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Abonnement ID: " + id));
  model.addAttribute("abonnement", abonnement);
  model.addAttribute("client", abonnement.getClient());
  return "editAbonnement";
 }
 @GetMapping("/admin/AbonnementClient")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
 public String AbonnementClient(Model model,Long id){
  Client client=clientService.findClient(id).orElse(null);
  Collection<Abonnement> abonnement=abonnementService.getByClient(client);
  model.addAttribute("client",client);
  model.addAttribute("abonnement",abonnement);
  return "AbonnementClient";};
 @GetMapping("/logout")
 public String logout (){

  return "redirect:/logout";
 }
}
