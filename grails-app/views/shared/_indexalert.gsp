<g:if test="${flash.message}">
<div class="${flash.class} m-4 text-center">
<h4 class="alert-heading">${flash.title}</h4>
 <hr>
  <p class="mb-0">${flash.message}</p>
</div>
</g:if>