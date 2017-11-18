<html>
    <head>
        <meta name="layout" content="layout"/>
		<asset:javascript src="config.index.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar-admin">
        </g:applyLayout>

        <p>
        <h2>Config</h2>
        </p>

        <form id="configForm">
        <table class="table">
        <tbody>
        <tr>
        <td>
        <label for="shorttokentimer">Short Token Timer</label>
        </td>
        <td>
        <select class="form-control" id="shorttokentimer">
        <option value="${30 * 60 * 1000}">30 minutes</option>
        <option value="${20 * 60 * 1000}">20 minutes</option>
        <option value="${10 * 60 * 1000}">10 minutes</option>
        <option value="${5 * 60 * 1000}">5 minutes</option>
        <option value="${60 * 1000}">1 minutes</option>
        </select>
        </td>
        </tr>
        <tr>
        <td>
        <label for="longtokentimer">Long Token Timer</label>
        </td>
        <td>
        <select class="form-control" id="longtokentimer">
        <option value="${1440 * 60 * 1000}">one day</option>
        <option value="${720 * 60 * 1000}">12 hours</option>
        <option value="${360 * 1000}">6 hours</option>
        <option value="${120 * 60 * 1000}">2 hours</option>
        <option value="${60 * 60 * 1000}">1 hour</option>
        </select>
        </td>
        </tr>
        <tr>
          <td>
            <label for="enablecaptcha">Enable Captcha<label>
          </td>
          <td>
            <input class="form-check-out" type="checkbox" id="enablecaptcha"/>
          </td>
        </tr>
        <tr>
          <td>
          <label for="title">Title</label>
          </td>
          <td>
            <input type="text" id="title" class="form-control"/>
          </td>
        </tr>
        <tr>
          <td>
          <label for="tagline">Tag line</label>
          </td>
          <td>
            <input type="text" id="tagline" class="form-control"/>
          </td>
        </tr>
        <tr>
          <td>
          <label for="favicon">Favicon (url)</label>
          </td>
          <td>
            <input type="text" id="favicon" class="form-control"/>
          </td>
        </tr>
        </tbody>
        </table>

        <input type="button" class="btn btn-primary btn-block" id="submit" value="Submit"/>

        </form>

    </body>
</html>
